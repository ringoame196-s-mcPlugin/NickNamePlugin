package com.github.ringoame196_s_mcPlugin.Manager

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File

class NickNameManager(plugin: Plugin) {
    private val dataBaseManager = DataBaseManager()
    private val dataBaseFilePath = "${plugin.dataFolder.path}\\playerData.db"
    fun setNickName(player: Player, nickName: String) {
        val playerName = player.name
        changeName(player, nickName)

        if (nickName == playerName) {
            resetNickName(player) // データベースから削除する
        } else {
            saveNickName(player, nickName) // データベースに保存
        }
    }

    fun changeName(player: Player, nickName: String) {
        val supportedColorNickName = "${supportedColorCode(nickName)}§f"
        player.setDisplayName(supportedColorNickName)
        player.setPlayerListName(supportedColorNickName)
    }

    fun makeTable() {
        if (!File(dataBaseFilePath).exists()) { // ファイルが無ければ
            val command = "CREATE TABLE IF NOT EXISTS NickNameTable (uuid TEXT PRIMARY KEY, nickname TEXT)"
            dataBaseManager.runSQLCommand(dataBaseFilePath, command)
        }
    }

    fun acquisitionNickname(player: Player): String? {
        val uuid = player.uniqueId
        val command = "SELECT nickname FROM NickNameTable WHERE uuid = ?;"
        // データベースから結果を取得
        val nickname = dataBaseManager.acquisitionStringValue(dataBaseFilePath, command, listOf(uuid), "nickname")

        return nickname
    }

    private fun saveNickName(player: Player, nickName: String) {
        val registrationValue = acquisitionNickname(player)
        val uuid = player.uniqueId

        if (registrationValue == null) {
            // 新しいレコードを挿入する
            val command = "INSERT INTO NickNameTable (uuid, nickname) VALUES (?, ?);"
            dataBaseManager.runSQLCommand(dataBaseFilePath, command, listOf(uuid, nickName)) // 保存
        } else {
            // 既存のレコードを更新する
            val command = "UPDATE NickNameTable SET nickname = ? WHERE uuid = ?;"
            dataBaseManager.runSQLCommand(dataBaseFilePath, command, listOf(nickName, uuid)) // 保存
        }
    }

    private fun resetNickName(player: Player) {
        val uuid = player.uniqueId
        val command = "DELETE FROM NickNameTable WHERE uuid = ?"
        dataBaseManager.runSQLCommand(dataBaseFilePath, command, listOf(uuid)) // 削除する
    }

    fun supportedColorCode(text: String): String {
        // &->§に変えることでカラーコード対応
        return text.replace("&", "§")
    }
}
