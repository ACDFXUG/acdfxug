#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
#include <algorithm>
using namespace std;
// Define the board size
const int N = 3;
// Define the target state
const vector<vector<int>> target = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
};

void printState(vector<vector<int>> p){
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            printf(j==2?"%d\n":"%d ",p[i][j]);
        }
    }
    printf("\n");
}
// Define a structure to represent a state
struct State {
    vector<vector<int>> board;
    int cost;
    int heuristic;
    int row;
    int col;
};
// Define a custom comparator for priority_queue
struct Compare {
    bool operator()(const State& a, const State& b) const {
        return a.cost + a.heuristic > b.cost + b.heuristic;
    }
};
// Define a function to calculate the heuristic value (Manhattan distance)
int calculateHeuristic(const vector<vector<int>>& board) {
    int heuristic = 0;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (board[i][j] != 0) {
                int targetRow = (board[i][j] - 1) / N;
                int targetCol = (board[i][j] - 1) % N;
                heuristic += abs(i - targetRow) + abs(j - targetCol);
            }
        }
    }
    return heuristic;
}
// Define a function to check if a state is valid
bool isValid(int row, int col) {
    return row >= 0 && row < N && col >= 0 && col < N;
}
// Define a function to generate child states
vector<State> generateChildren(const State& currentState) {
    vector<State> children;
    vector<pair<int, int>> directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (const auto& dir : directions) {
        int newRow = currentState.row + dir.first;
        int newCol = currentState.col + dir.second;
        if (isValid(newRow, newCol)) {
            State childState = currentState;
            swap(childState.board[currentState.row][currentState.col], childState.board[newRow][newCol]);
            childState.row = newRow;
            childState.col = newCol;
            childState.cost++;
            childState.heuristic = calculateHeuristic(childState.board);
            children.push_back(childState);
        }
    }
    return children;
}
// Define the A algorithm function
void A(const vector<vector<int>>& initialState) {
    priority_queue<State, vector<State>, Compare> openSet;
    unordered_set<string> closedSet;
    State initial;
    initial.board = initialState;
    initial.cost = 0;
    initial.heuristic = calculateHeuristic(initial.board);
    // Find the position of the empty cell
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (initial.board[i][j] == 0) {
                initial.row = i;
                initial.col = j;
                break;
            }
        }
    }
    openSet.push(initial);
    while (!openSet.empty()) {
        State current = openSet.top();
        openSet.pop();
        printState(current.board);
        // Check if the current state is the target state
        if (current.board == target) {
            printf("Goal state reached in %d steps\n",current.cost);
            return;
        }
        // Generate child states
        vector<State> children = generateChildren(current);
        for (const auto& child : children) {
            // Check if the child state is in the closed set
            if (closedSet.find(to_string(child.board[0][0]) + to_string(child.board[0][1]) + to_string(child.board[0][2]) +
                               to_string(child.board[1][0]) + to_string(child.board[1][1]) + to_string(child.board[1][2]) +
                               to_string(child.board[2][0]) + to_string(child.board[2][1]) + to_string(child.board[2][2])) == closedSet.end()) {
                // Add the child state to the open set
                openSet.push(child);
                // Add the child state to the closed set
                closedSet.insert(to_string(child.board[0][0]) + to_string(child.board[0][1]) + to_string(child.board[0][2]) +
                                 to_string(child.board[1][0]) + to_string(child.board[1][1]) + to_string(child.board[1][2]) +
                                 to_string(child.board[2][0]) + to_string(child.board[2][1]) + to_string(child.board[2][2]));
            }
        }
    }
    printf("No solution found\n");
}
int main() {
    vector<vector<int>> initialState = {{2, 8, 3},
                                        {1, 0, 4},
                                        {7, 6, 5}};
    A(initialState);
}