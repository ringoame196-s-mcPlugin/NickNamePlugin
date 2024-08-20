package com.github.ringoame196_s_mcPlugin

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class DataBaseManager() {
    fun runSQLCommand(dbFilePath: String, command: String) {
        var statement: Statement? = null
        try {
            statement = connection(dbFilePath) // 接続
            statement?.executeUpdate(command)
        } catch (e: SQLException) {
            // エラーハンドリング
            println("SQL Error: ${e.message}")
        } finally {
            disconnect(statement?.connection) // 切断
        }
    }

    fun acquisitionStringValue(dbFilePath: String, command: String, label: String): String? {
        var value: String? = null
        var statement: Statement? = null
        try {
            statement = connection(dbFilePath) // 接続
            val resultSet = statement?.executeQuery(command)
            value = if (resultSet != null && resultSet.next()) {
                resultSet.getString(label)
            } else {
                null // 結果が存在しない場合はnullを返す
            }
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
