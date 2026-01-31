package ComputerScience.TJU.Practice;

import module java.base;

public class Cache {
    public CacheConfig config;
    public CacheSet[] sets;
    public long globalCnter;
    public String traceFile;

    long reads,readMisses,writes,writeMisses,writeBacks;

    int numSets,indexBits,offsetBits,tagBits;

    Cache(CacheConfig config,String traceFile){
        this.config=config;
        this.traceFile=traceFile;
        this.globalCnter=0;
        this.reads=0;
        this.readMisses=0;
        this.writes=0;
        this.writeMisses=0;
        this.writeBacks=0;
        this.numSets=(int)(config.totalSize/(config.blockSize*config.assoc));
        this.indexBits=0;
        this.offsetBits=0;
        this.tagBits=0;

        int tmp=config.blockSize;
        for(;tmp>1;tmp>>=1) ++offsetBits;
        tmp=numSets;
        for(;tmp>1;tmp>>=1) ++indexBits;
        this.tagBits=32-indexBits-offsetBits;
        this.sets=new CacheSet[numSets];
        for(int i=0;i<numSets;++i) sets[i]=new CacheSet(config.assoc);

        for(int i=0;i<numSets;++i) sets[i].ageCnter=0;
    }

    /**
     * 解析地址
     * @param addr 地址
     * @return [tag,index,offset]
     */
    public long[] parseAddress(long addr){
        long[] ans=new long[3];
        ans[2]=addr&((1<<offsetBits)-1);
        addr>>=offsetBits;
        ans[1]=addr&((1<<indexBits)-1);
        addr>>=indexBits;
        ans[0]=addr;
        return ans;
    }

    public int findBlock(int index,long tag){
        for(int i=0;i<sets[index].blocks.length;++i){
            if(sets[index].blocks[i].isValid&&sets[index].blocks[i].tag==tag)
                return i;
        }
        return -1;
    }

    public int selectVictim(int index){
        int vic=-1;
        for(int i=0;i<config.assoc;++i){
            if(!sets[index].blocks[i].isValid) return i;
        }

        if(config.replacementPolicy==0){
            long min=Long.MAX_VALUE;
            for(int i=0;i<config.assoc;++i){
                if(sets[index].blocks[i].count<min){
                    min=sets[index].blocks[i].count;
                    vic=i;
                }
            }
        }else{
            long min=Long.MAX_VALUE;
            for(int i=0;i<config.assoc;++i){
                if(sets[index].blocks[i].count<min){
                    min=sets[index].blocks[i].count;
                    vic=i;
                }
            }
        }

        return vic;
    }

    void loadBlock(int index,int blockId,long tag){
        sets[index].blocks[blockId].isValid=true;
        sets[index].blocks[blockId].tag=tag;
        sets[index].blocks[blockId].isDirty=false;

        if(config.replacementPolicy==0){
            sets[index].blocks[blockId].count=globalCnter;
        }else{
            sets[index].blocks[blockId].count=sets[index].ageCnter+1;
        }
    }

    void updateCount(int index,int blockId){
        if(config.replacementPolicy==0){
            sets[index].blocks[blockId].count=globalCnter;
        }else{
            sets[index].blocks[blockId].count+=1;
        }
    }

    boolean read(long addr){
        long[] parsed=parseAddress(addr);
        ++reads;
        ++globalCnter;
        int index=(int)parsed[1];
        
        int blockId=findBlock(index,parsed[0]);
        if(blockId==-1){
            ++readMisses;
            int blockToUse=selectVictim(index);
            if(config.replacementPolicy==1&&sets[index].blocks[blockToUse].isValid){
                sets[index].ageCnter=sets[index].blocks[blockToUse].count;
            }

            if(config.writePolicy==0&&sets[index].blocks[blockToUse].isDirty&&sets[index].blocks[blockToUse].isValid){
                ++writeBacks;
            }

            loadBlock(index,blockToUse,parsed[0]);
            return false;
        }else{
            updateCount(index,blockId);
            return true;
        }
    }

    boolean write(long addr){
        long[] parsed=parseAddress(addr);
        ++writes;
        ++globalCnter;
        int index=(int)parsed[1];
        
        int blockId=findBlock(index,parsed[0]);
        if(blockId==-1){
            ++writeMisses;
            if(config.writePolicy==0){
                int blockToUse=selectVictim(index);
                if(config.replacementPolicy==1&&sets[index].blocks[blockToUse].isValid){
                    sets[index].ageCnter=sets[index].blocks[blockToUse].count;
                }

                if(sets[index].blocks[blockToUse].isDirty&&sets[index].blocks[blockToUse].isValid){
                    ++writeBacks;
                }

                loadBlock(index,blockToUse,parsed[0]);
                sets[index].blocks[blockToUse].isDirty=true;
            }else{}
            return false;
        }else{
            updateCount(index,blockId);
            if(config.writePolicy==0){
                sets[index].blocks[blockId].isDirty=true;
            }
            return true;
        }
    }

    void printContents(){
        for(int i=0;i<numSets;++i){
            System.out.print("set "+i+":");
            for(int j=0;j<config.assoc;++j){
                if(sets[i].blocks[j].isValid){
                    System.out.printf(" %x",sets[i].blocks[j].tag);
                    if(config.writePolicy==0){
                        System.out.print(sets[i].blocks[j].isDirty?" D":"  ");
                    }else{
                        System.out.print("  ");
                    }
                }else{
                    System.out.print(" _ _");
                }
            }
            System.out.println();
        }
    }

    void printStats(){ 
        System.out.println("  ===== Simulator configuration =====");
        System.out.println("  L1_BLOCKSIZE:                      "+config.blockSize);
        System.out.println("  L1_SIZE:                           "+config.totalSize);
        System.out.println("  L1_ASSOC:                           "+config.assoc);
        System.out.println("  L1_REPLACEMENT_POLICY:              "+config.replacementPolicy);
        System.out.println("  L1_WRITE_POLICY:                     "+config.writePolicy);
        System.out.println("  trace_file:                         "+traceFile);
        System.out.println("  ===================================\n");
        System.out.println("===== L1 contents =====");
        printContents();
        System.out.println();

        var totalAcc=reads+writes;
        double missRate=(totalAcc == 0) ? 0 : (double)(readMisses + writeMisses) / totalAcc;
        long traffic;
        if(config.writePolicy==0){
            traffic=readMisses + writeMisses + writeBacks;
        }else{
            traffic=readMisses + writes;
        }

        System.out.println("  ====== Simulation results (raw) ======");
        System.out.println("  a. number of L1 reads:            "+reads);
        System.out.println("  b. number of L1 read misses:      "+readMisses);
        System.out.println("  c. number of L1 writes:           "+writes);
        System.out.println("  d. number of L1 write misses:     "+writeMisses);
        System.out.printf( "  e. L1 miss rate:                  %.4f\n",missRate);
        System.out.println("  f. number of writebacks from L1:  "+writeBacks);
        System.out.println("  g. total memory traffic:          "+traffic);
        System.out.println();

        double ht=0.25 + 2.5 * (config.totalSize / (512*1024.0)) + 0.025 * (config.blockSize / 16.0) +
                0.025 * (config.assoc);

        double mp = 20 + 0.5 * (config.blockSize / 16.0);

        double aat = ht + (missRate * mp);

        System.out.println("  ==== Simulation results (performance) ====");
        System.out.printf("  1. average access time:          %.4f ns\n",aat);
    }
    public static void main(String[] args) 
    throws IOException{
        Scanner sc=new Scanner(System.in);
        int blockSize=sc.nextInt(),
            totalSize=sc.nextInt(),
            assoc=sc.nextInt(),
            replacementPolicy=sc.nextInt(),
            writePolicy=sc.nextInt();
        CacheConfig config=new CacheConfig(blockSize,totalSize,assoc,replacementPolicy,writePolicy);
        Cache cache=new Cache(config,sc.next());

        Path trace=Path.of("trace.txt");
        var traceReader=Files.newBufferedReader(trace);
        for(String line;(line=traceReader.readLine())!=null;){
            String[] op$addr=line.split(" ");
            long addr=Long.parseLong(op$addr[1],16);
            switch(op$addr[0]){
                case "r"->cache.read(addr);
                case "w"->cache.write(addr);
            }
        }
        traceReader.close();
        cache.printStats();
        sc.close();
    }
}

class CacheConfig{
    public int blockSize,
        totalSize,
        assoc,
        replacementPolicy,
        writePolicy;
    public CacheConfig(int blockSize,int totalSize,int assoc,int replacementPolicy,int writePolicy){
        this.blockSize=blockSize;
        this.totalSize=totalSize;
        this.assoc=assoc;
        this.replacementPolicy=replacementPolicy;
        this.writePolicy=writePolicy;
    }
}

class CacheBlock{
    boolean isValid,isDirty;
    long tag,count;
    CacheBlock(boolean isValid,boolean isDirty,long tag,long count){
        this.isValid=isValid;
        this.isDirty=isDirty;
        this.tag=tag;
        this.count=count;
    }
    CacheBlock(){
        this(false,false,0,0);
    }
}

class CacheSet{
    CacheBlock[] blocks;
    long ageCnter;
    CacheSet(int assoc){
        this.blocks=new CacheBlock[assoc];
        for(int i=0;i<assoc;++i){
            this.blocks[i]=new CacheBlock();
        }
        this.ageCnter=0;
    }
}