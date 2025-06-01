#include <iostream>
#include <print>
#include <vector>
#include <concepts>

/**
 * @brief 插入排序实现
 * 
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
    int length(){
        return arr.size();
    }
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

int main(){
    InsertSort<int> is({3,2,1,5,4,10,7,8,6,9});
    std::println("{}",is.direct_insert());
    std::println("{}",is.binary_insert());
    std::println("{}",is.shell_insert());

    ExchangeSort<int> es({3,2,1,5,4,10,7,8,6,9});
    std::println("{}",es.bubble_sort());
    std::println("{}",es.quick_sort());
}