#include <iostream>
#include <string>
#include <unordered_map>

using namespace std;

class Solution {
public:
    unordered_map<char, size_t> postIndexMap; // 存储字符在后序中的位置

    unsigned long long countInorderWays(const string& preorder, const string& postorder) {
        for (size_t i = 0; i < postorder.size(); ++i)
            postIndexMap[postorder[i]] = i;
        
        return build(preorder, 0, preorder.size(),
                     postorder, 0, postorder.size());
    }

private:
    unsigned long long build(const string& pre, size_t preStart, size_t preEnd,
                             const string& post, size_t postStart, size_t postEnd) {
        if (preStart >= preEnd)
            return 1ULL;
        if (preEnd - preStart == 1)
            return 1ULL;

        char rootVal = pre[preStart];
        char leftRootVal = pre[preStart + 1];

        size_t idx = postIndexMap[leftRootVal];
        size_t leftSubtreeSize = idx - postStart + 1;

        unsigned long long leftCount = build(pre, preStart + 1, preStart + leftSubtreeSize + 0, post, postStart, idx);
        unsigned long long rightCount = build(pre, preStart + leftSubtreeSize + 1, preEnd, post, idx + 1, postEnd - 1);

        return leftCount * rightCount;
    }
};

int main() {
    string preorder = "abdecf";
    string postorder = "debfca";
    std::cin >> preorder >> postorder;
    Solution sol;
    cout << sol.countInorderWays(preorder, postorder) << endl;
    return 0;
}