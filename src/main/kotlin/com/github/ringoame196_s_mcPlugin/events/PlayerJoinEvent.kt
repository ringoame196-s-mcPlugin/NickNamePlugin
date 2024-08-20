package com.github.ringoame196_s_mcPlugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin

class PlayerJoinEvent(plugin: Plugin) : Listener {
    private val nickNameManager = NickNameManager(plugin)
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        val nickName = nickNameManager.acquisitionNickname(player) ?: player.name
        nickNameManager.changeName(player, nickName) // 名前を変更する

        setJoinPlayerMessage(e, nickName) // joinメッセージを変更する
    }
    private fun setJoinPlayerMessage(e: PlayerJoinEvent, nickName: String) {
        val player = e.player
        val supportedColorNickName = nickNameManager.supportedColorCode(nickName)
        val playerName = player.name
        val joinMessage = e.joinMessage?.replace(playerName, "$supportedColorNickName§e")
        e.joinMessage = joinMessage
    }
}
