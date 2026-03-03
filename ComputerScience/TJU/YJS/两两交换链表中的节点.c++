#include <print>

struct ListNode{
    int val;
    ListNode *next;
    ListNode(int val=0,ListNode *next=nullptr):
    val(val),next(next){}
};

ListNode* swapPairs(ListNode* head) {
    if(head==nullptr) return nullptr;
    ListNode *re;
    if(head->next==nullptr) return head;
    else re=head->next;
    
    ListNode* dummy = new ListNode(0);
    dummy->next = head;
    
    // 2. 使用 prev 指针指向“待交换对”的前一个节点
    ListNode* prev = dummy;
    
    // 3. 循环条件：确保后面至少还有两个节点可以交换
    while (prev->next != nullptr && prev->next->next != nullptr) {
        ListNode* first = prev->next;       // 待交换的第 1 个节点
        ListNode* second = prev->next->next;// 待交换的第 2 个节点
        
        // 4. 执行交换 (画图理解：prev -> 1 -> 2 -> 3  变为  prev -> 2 -> 1 -> 3)
        prev->next = second;        // prev 指向 2
        first->next = second->next; // 1 指向 3 (关键：连接后续链表)
        second->next = first;       // 2 指向 1
        
        // 5. 移动 prev 指针，准备处理下一对
        // 此时 first 已经是这一对的第二个节点了，所以 prev 移到 first
        prev = first;
    }
    
    ListNode* newHead = dummy->next;
    delete dummy; // 释放内存
    return newHead;
}