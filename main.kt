fun main() {
    val boardSize = 8
    val board = Array(boardSize) { IntArray(boardSize) { -1 } } 
    val moves = arrayOf(
        Pair(2, 1), Pair(1, 2), Pair(-1, 2), Pair(-2, 1),
        Pair(-2, -1), Pair(-1, -2), Pair(1, -2), Pair(2, -1)
    )

    fun isValidMove(x: Int, y: Int): Boolean {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize && board[x][y] == -1
    }

    fun countMoves(x: Int, y: Int): Int {
        var count = 0
        for ((dx, dy) in moves) {
            val newX = x + dx
            val newY = y + dy
            if (isValidMove(newX, newY)) count++
        }
        return count
    }

    fun printBoard() {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                print("${board[i][j]} ")
            }
            println()
        }
    }

    fun solve(x: Int, y: Int, moveCount: Int): Boolean {
        if (moveCount == boardSize * boardSize) {
            return true
        }

        val nextMoves = mutableListOf<Pair<Int, Int>>()

        for ((dx, dy) in moves) {
            val newX = x + dx
            val newY = y + dy
            if (isValidMove(newX, newY)) {
                nextMoves.add(Pair(newX, newY))
            }
        }

        nextMoves.sortBy { countMoves(it.first, it.second) }

        for ((newX, newY) in nextMoves) {
            board[newX][newY] = moveCount
            if (solve(newX, newY, moveCount + 1)) {
                return true
            }
            board[newX][newY] = -1
        }

        return false
    }

    val startX = 0
    val startY = 0

    board[startX][startY] = 0

    if (solve(startX, startY, 1)) {
        println("solução:")
        printBoard()
    } else {
        println("não há solução possível.")
    }
}
