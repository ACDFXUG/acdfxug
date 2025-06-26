#include <iostream>
#include <print>
#include <vector>
#include <concepts>

/**
 * @brief 插入排序实现
 * @tparam T 
 */
template<std::totally_ordered T>
class InsertSort{
private:
    std::vector<T> arr;
public:
    InsertSort(std::vector<T> arr):arr(arr){}
    int length(){
        arr.size();
    }
    /**
     * 使用直接插入排序算法对数组进行排序
     * 
     * 此函数创建一个新的数组，使用直接插入排序算法对原数组的副本进行排序，
     * 并返回排序后的新数组。直接插入排序的基本思想是将一个记录插入到已经
     * 排好序的序列中，从而得到一个新的、记录数增加1的有序序列。
     * 
     * @return 返回一个新的、排序后的数组
     */
    std::vector<T> direct_insert(){
        // 创建原数组的副本，避免修改原数组
        std::vector<T> new_arr(arr);
        // 从第二个元素开始遍历，因为第一个元素默认已排序
        for(int i=1;i<arr.size();i++){
            // 将当前元素存储在temp中，以便插入到正确的位置
            T temp=new_arr[i];
            int j=i-1;
            // 向左移动元素，直到找到temp的正确插入位置
            while(j>=0&&new_arr[j]>temp){
                new_arr[j+1]=new_arr[j];
                j--;
            }
            // 将temp插入到找到的位置
            new_arr[j+1]=temp;
        }
        // 返回排序后的新数组
        return new_arr;
    }
    // 使用二分查找进行插入排序的函数
    // 该函数对模板类中的数组arr进行排序，并返回一个新的排序后的vector
    std::vector<T> binary_insert(){
        // 创建一个新数组new_arr，初始内容与原数组arr相同
        std::vector<T> new_arr(arr);
        // 遍历数组，从第二个元素开始，因为第一个元素默认已排序
        for(int i=1;i<arr.size();i++){
            // 将当前元素存储在临时变量temp中
            T temp=new_arr[i];
            // 初始化二分查找的左右指针
            int left=0,right=i-1;
            // 使用二分查找确定当前元素temp的插入位置
            while(left<=right){
                // 计算中间位置
                int mid=(left+right)/2;
                // 如果中间位置的元素大于temp，则temp应该插入到mid的左侧
                if(new_arr[mid] > temp){
                    right=mid-1;
                }else{
                    // 否则，temp应该插入到mid的右侧
                    left=mid+1;
                }
            }
            // 将插入位置右侧的元素向右移动，为temp腾出插入空间
            for(int j = i; j > left; j--){
                new_arr[j] = new_arr[j-1];
            }
            // 将temp插入到正确的位置
            new_arr[left] = temp;
        }
        // 返回新的排序后的数组
        return new_arr;
    }
    // 使用希尔排序算法对数组进行排序
    // 希尔排序是插入排序的一种高效率的改进版本，它通过比较相距一定间隔的元素来工作
    // 随着算法的进行，间隔会逐渐减小，最后变为1，此时算法退化为普通的插入排序
    std::vector<T> shell_insert(this const InsertSort& self){
        // 复制原始数组，以便在新数组上进行排序操作
        std::vector new_arr(self.arr);
        
        // 初始化间隔序列，使用数组长度的一半作为初始间隔
        // 间隔会逐渐减小，直到变为1
        for(int gap=new_arr.size()/2;gap>0;gap>>=1){
            // 从间隔开始遍历数组，对每个元素进行插入排序
            for(int i=gap;i<new_arr.size();i++){
                // 如果当前元素小于其间隔前的元素，则需要进行插入操作
                if(new_arr[i]<new_arr[i-gap]){
                    // 保存当前元素到临时变量
                    T temp=new_arr[i];
                    // 初始化插入位置
                    int j=i-gap;
                    // 将所有大于当前元素的元素向后移动间隔的位置
                    while(j>=0&&new_arr[j]>temp){
                        new_arr[j+gap]=new_arr[j];
                        // 更新插入位置
                        j-=gap;
                    }
                    // 将当前元素插入到正确的位置
                    new_arr[j+gap]=temp;
                }
            }
        }
        // 返回排序后的数组
        return new_arr;
    }
};

template<std::totally_ordered T>
class ExchangeSort{
private:
    std::vector<T> arr;
    static int split(std::vector<T> &tmp,int left,int right){
        T pivot=tmp[left];
        while(left<right){
            for(;left<right&&tmp[right]>=pivot;--right);
            tmp[left]=tmp[right];
            for(;left<right&&tmp[left]<=pivot;++left);
            tmp[right]=tmp[left];
        }
        tmp[left]=pivot;
        return left;
    };
    static void quick_sort_helper(std::vector<T> &tmp,int left,int right){
        if(left<right){
            int pivot_index=split(tmp,left,right);
            quick_sort_helper(tmp,left,pivot_index-1);
            quick_sort_helper(tmp,pivot_index+1,right);
        }
    }
public:
    ExchangeSort(std::vector<T> arr):arr(arr){}
    std::vector<T> bubble_sort(){
        // 创建一个新数组，初始内容与原数组arr相同
        std::vector<T> new_arr(arr);
        // 遍历数组，从第一个元素开始，到倒数第二个元素结束
        for(int i=0;i<new_arr.size()-1;i++){
            for(int j=0;j<new_arr.size()-1-i;j++){
                // 如果当前元素大于下一个元素，则交换它们的位置
                if(new_arr[j]>new_arr[j+1]){
                    std::swap(new_arr[j],new_arr[j+1]);
               }
            }
        }
        return new_arr;
    }
    std::vector<T> quick_sort(){
        // 创建一个新数组，初始内容与原数组arr相同
        std::vector<T> new_arr(arr);
        // 调用快速排序算法对数组进行排序
        quick_sort_helper(new_arr,0,new_arr.size()-1);
        // 返回排序后的数组
        return new_arr;
    }
};

template<std::totally_ordered T>
class SelectSort{
private:
    std::vector<T> arr; 
    /**
     * 调整堆以保持最大堆或最小堆的性质
     * @tparam T 元素类型
     * @param tmp 进行调整的堆
     * @param k 当前需要调整的根节点索引
     * @param len 堆的有效长度
     * 
     * 此函数根据max模板参数决定是构建最大堆还是最小堆
     * 如果max为true，则构建最大堆，否则构建最小堆
     * 通过比较根节点的子节点来确定是否需要交换元素，以保持堆的性质
     */
    template<bool max=true>
    static void adjust_heap(std::vector<T> &tmp,int k,int len){
        T root=tmp[k];
        for(int i=2*k;i<=len;i<<=1){
            if constexpr(max){
                // 在最大堆中，选择较大的子节点
                if(i<len&&tmp[i]<tmp[i+1]){
                    i++;
                }
                // 如果根节点小于子节点，则交换，以保持最大堆的性质
                if(root<tmp[i]){
                    tmp[k]=tmp[i];
                    k=i;
                }else break;
            }else{
                // 在最小堆中，选择较小的子节点
                if(i<len&&tmp[i]>tmp[i+1]){
                    i++;
                }
                // 如果根节点大于子节点，则交换，以保持最小堆的性质
                if(root>tmp[i]){
                    tmp[k]=tmp[i];
                    k=i;
                }else break;
            }
        }
        tmp[k]=root;
    }
    /**
     * @brief 从给定数组构建堆。
     * 
     * 此函数使用 `adjust_heap` 函数从数组构建堆，以便进行堆排序算法。
     * 默认情况下，它会构建一个最大堆（通过参数 `max=true` 控制）。
     * 
     * @param tmp 要构建成堆的数组。
     * @param len 数组的长度。
     * @tparam T 数组中元素的类型。
     */
    template<bool max=true>
    static void build_heap(std::vector<T> &tmp, int len) {
        // 从最后一个非叶子节点开始，逆序构建堆。
        for(int i = len / 2; i >= 1; i--) {
            // 调用 adjust_heap 函数调整当前节点位置的堆结构。
            adjust_heap<max>(tmp, i, len);
        }
    }
    /**
     * @brief 堆排序算法实现
     *
     * 此函数使用堆排序对数组进行排序。默认情况下使用最大堆（max=true）。
     * 排序过程如下：
     * 1. 创建一个新的数组 `new_arr`，并在其第一个位置留空（索引0），以方便堆操作；
     * 2. 将原始数组复制到 `new_arr` 中（从索引1开始）；
     * 3. 调用 `build_heap` 构建一个堆；
     * 4. 进行堆排序：每次将堆顶元素（最大值或最小值）与堆的最后一个元素交换，
     *    然后调整堆以保持堆性质，并将该元素添加到结果数组 `ans` 中；
     * 5. 最后将剩余的堆顶元素加入结果数组。
     *
     * @return 返回排序后的数组
     */
    template<bool max= true >
    std::vector<T> heap_sort(){
        std::vector<T> new_arr(1); // 初始化一个大小为1的数组，索引0不用于存储数据
        new_arr.insert(new_arr.end(), arr.begin(), arr.end()); // 将原数组复制到new_arr中（从索引1开始）

        build_heap<max>(new_arr, arr.size()); // 构建初始堆

        std::vector<T> ans; // 存储排序后的结果
        ans.reserve(arr.size()); // 预分配空间，提高性能

        // 堆排序主循环：从堆中依次取出最大（或最小）元素
        for(int i = arr.size(); i > 1; i--){
            std::swap(new_arr[1], new_arr[i]); // 将堆顶元素与最后一个元素交换
            adjust_heap<max>(new_arr, 1, i - 1); // 调整堆，排除已排序的最后一个元素
            ans.push_back(new_arr[i]); // 将当前最大（或最小）元素加入结果数组
        }

        ans.push_back(new_arr[1]); // 将最后一个元素加入结果数组
        return ans; // 返回排序后的数组
    }
public:
    SelectSort(std::vector<T> arr):arr(arr){}
    // 使用选择排序算法对数组进行排序
    // 该函数返回一个新的、已排序的vector，而不修改原始vector
    std::vector<T> simple_select(){
        // 创建一个新vector，是原始vector的副本
        std::vector<T> new_arr(arr);
        // 遍历新vector，寻找每个位置上的最小元素
        for(int i=0;i<new_arr.size()-1;i++){
            // 假设当前位置的元素是最小的
            int min_idx=i;
            // 从当前位置的下一个元素开始，遍历到vector的末尾
            for(int j=i+1;j<new_arr.size();j++){
                // 如果找到更小的元素，则更新最小元素的位置
                if(new_arr[j]<new_arr[min_idx]){
                    min_idx=j;
                }
            }
            // 将当前位置的元素与最小元素交换位置
            std::swap(new_arr[i],new_arr[min_idx]);
        }
        // 返回排序后的新vector
        return new_arr;
    }
    /**
     * @brief 大顶堆
     * 
     * @return std::vector<T> 
     */
    std::vector<T> max_heap_sort(){
        return heap_sort<true>();
    }
    /**
     * @brief 小顶堆
     * 
     * @return std::vector<T> 
     */
    std::vector<T> min_heap_sort(){
        return heap_sort<false>();
    }
};

template<std::totally_ordered T>
class MergeSort{
private:
    std::vector<T> arr;
    static void merge(std::vector<T> &arr,int l,int mid,int r){
        std::vector<T> tmp(arr.size()+1);
        int i,j,k;
        for(k=l;k<=r;++k) tmp[k]=arr[k];
        for(i=l,j=mid+1,k=i;i<=mid&&j<=r;++k){
            if(tmp[i]<=tmp[j]) arr[k]=tmp[i++];
            else arr[k]=tmp[j++];
        }
        while(i<=mid) arr[k++]=tmp[i++];
        while(j<=r) arr[k++]=tmp[j++];
    }
    static void merge_sort(std::vector<T> &arr,int l,int r){
        if(l>=r) return;
        int mid=(l+r)/2;
        merge_sort(arr,l,mid);
        merge_sort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
public:
    MergeSort(std::vector<T> arr):arr(arr){}
    std::vector<T> merge_sort(){ 
        std::vector<T> new_arr(arr);
        merge_sort(new_arr,0,new_arr.size()-1 );
        return new_arr;
    }
};

int main(){
    InsertSort<int> is({3,2,1,5,4,10,7,8,6,9});
    std::println("Insert Sort:");
    std::println("{}",is.direct_insert());
    std::println("{}",is.binary_insert());
    std::println("{}",is.shell_insert());

    ExchangeSort<int> es({3,2,1,5,4,10,7,8,6,9});
    std::println("Exchange Sort:");
    std::println("{}",es.bubble_sort());
    std::println("{}",es.quick_sort());

    SelectSort<int> ss({3,2,1,5,4,10,7,8,6,9});
    std::println("Select Sort:");
    std::println("{}",ss.simple_select());
    std::println("{}",ss.min_heap_sort());

    MergeSort<int> ms({3,2,1,5,4,10,7,8,6,9});
    std::println("Merge Sort:");
    std::println("{}",ms.merge_sort());
}