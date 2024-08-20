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
        val nickname = nickNameManager.acquisitionNickname(player) ?: player.name
        nickNameManager.setNickName(player, nickname.toString())
    }
}
