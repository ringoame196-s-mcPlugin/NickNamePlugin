package com.github.ringoame196_s_mcPlugin

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Command : CommandExecutor {
    private val nickNameManager = NickNameManager()
    private val playerManager = PlayerManager()
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) { // 入力不足
            return false
        }

        val selectPlayer = sender as? Player?
        val nickName = args[0]

        if (nickNameManager.isChangeName(sender)) { // 権限を持っていないとき
            when (args.size) {
                1 -> setNickName(sender, nickName, selectPlayer) // 自分自身のニックネームを設定
                2 -> setOtherPlayerNickName(sender, nickName, args) // 他プレイヤーのニックネームを設定
            }
        } else { // 権限を持っていない
            sendNotHaveAuthority(sender)
        }

        return true
    }

    private fun sendNotHaveAuthority(sender: CommandSender) {
        val authorityMissingMessage = "${ChatColor.RED}このコマンドを実行する権限がありません"
        sender.sendMessage(authorityMissingMessage)
    }

    private fun setOtherPlayerNickName(sender: CommandSender, nickName: String, args: Array<out String>) {
        if (nickNameManager.isAdmin(sender)) {
            val selectPlayerName = args[1]
            val selectPlayer = Bukkit.getPlayer(selectPlayerName)
            setNickName(sender, nickName, selectPlayer) // nicknameを設定する
        } else {
            // 権限を持っていない場合
            sendNotHaveAuthority(sender)
        }
    }

    private fun setNickName(sender: CommandSender, nickName: String, selectPlayer: Player?) {
        if (selectPlayer != null) {
            val selectPlayerName = selectPlayer.name
            val message = "${ChatColor.AQUA}${selectPlayerName}のニックネームを${nickName}にしました"
            playerManager.changeName(selectPlayer, nickName)
            sender.sendMessage(message)
        } else {
            val message = "${ChatColor.RED}プレイヤーを指定してください"
            sender.sendMessage(message)
        }
    }
}
