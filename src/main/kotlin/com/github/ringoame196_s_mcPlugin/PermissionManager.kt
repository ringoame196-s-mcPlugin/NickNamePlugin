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

    fun haveTempSetNickNameTag(sender: CommandSender, tag: String): Boolean {
        if (sender is Player) {
            val havePermission = sender.scoreboardTags.contains(tag)
            sender.scoreboardTags.remove(tag) // タグを消す
            return havePermission
        } else { // プレイヤー以外の場合は常にfalse
            return false
        }
    }
}
