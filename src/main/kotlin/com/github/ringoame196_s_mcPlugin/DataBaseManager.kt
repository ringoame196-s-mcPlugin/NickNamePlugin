package com.github.ringoame196_s_mcPlugin

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class DataBaseManager() {
    fun runSQLCommand(dbFilePath: String, command: String): Any? {
        var value: Any? = null
        var statement: Statement? = null
        try {
            statement = connection(dbFilePath) // 接続
            value = statement?.executeUpdate(command)
        } catch (e: SQLException) {
            // エラーハンドリング
            println("SQL Error: ${e.message}")
        } finally {
            disconnect(statement?.connection) // 切断
        }
        return value
    }

    private fun connection(dbFilePath: String): Statement? {
        val connection = DriverManager.getConnection("jdbc:sqlite:$dbFilePath")
        // SQLステートメントの作成
        val statement = connection.createStatement()
        statement.queryTimeout = 30 // タイムアウトの設定
        return statement
    }

    private fun disconnect(connection: Connection?) {
        connection?.close()
    }
}
