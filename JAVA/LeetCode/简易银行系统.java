package Java.LeetCode;

public class 简易银行系统 {
    private static class Bank{
        // Map<Integer,Long> bank;
        long[] bank;
        Bank(long[] balance){
            // this.bank=new HashMap<Integer,Long>(){{
            //     for(int i=0;i<balance.length;i++){
            //         put(i+1,balance[i]);
            //     }
            // }};
            this.bank=balance.clone();
        }
        boolean transfer(int account1, int account2, long money) {
            // if(account1<1||account2<1||account1>
            // bank.size()||account2>bank.size()) return false;
            // long MONEY1=bank.get(account1),
            // MONEY2=bank.get(account2);
            // if(MONEY1<money){
            //     return false;
            // }else{
            //     bank.put(account1,MONEY1-money);
            //     bank.put(account2,MONEY2+money);
            //     return true;
            // }
            if(account1<1||account2<1||account1>bank.length||account2>bank.length) return false;
            else if(bank[account1-1]<money) return false;
            else{
                bank[account1-1]-=money;
                bank[account2-1]+=money;
                return true;
            }
        }
        boolean deposit(int account, long money) {
            // if(account<1||account>bank.size()){
            //     return false;
            // }else{
            //     bank.put(account,bank.get(account)+money);
            //     return true;
            // }
            if(account<1||account>bank.length){
                return false;
            }else{
                bank[account-1]+=money;
                return true;
            }
        }
        boolean withdraw(int account, long money) {
            // if(account<1||account>bank.size()){
            //     return false;
            // }else{
            //     long MONEY=bank.get(account);
            //     if(MONEY<money){
            //         return false;
            //     }else{
            //         bank.put(account,MONEY-money);
            //         return true;
            //     }
            // }
            if(account<1||account>bank.length){
                return false;
            }else{
                if(bank[account-1]<money){
                    return false;
                }else{
                    bank[account-1]-=money;
                    return true;
                }
            }
        }
    }
    public static void main(String[] args) {
        Bank bank=new Bank(new long[]{10,100,20,50,30});
        System.out.println(bank.withdraw(3, 10));
        System.out.println(bank.transfer(5, 1, 20));
        System.out.println(bank.deposit(5, 20));
        System.out.println(bank.transfer(3, 4, 15));
        System.out.println(bank.withdraw(10, 50));
    }
}
