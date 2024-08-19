package com.github.ringoame196_s_mcPlugin

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PermissionManager {
    private val setNickNamePermission = "nickName.setting"
    private val adminPermission = "nickName.admin"

    fun haveSetNickNamePermission(sender: CommandSender): Boolean {
        return sender.hasPermission(setNickNamePermission) || sender.hasPermission(adminPermission)
    }

    fun haveAdminPermission(sender: CommandSender): Boolean {
        return sender.hasPermission(adminPermission)
    }

    fun haveTmpSetNickNameTag(player: Player, tag: String): Boolean {
        val havePermission = player.scoreboardTags.contains(tag)
        player.scoreboardTags.remove(tag) // タグを消す
        return havePermission
    }
}
