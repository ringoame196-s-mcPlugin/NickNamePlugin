package com.github.ringoame196_s_mcPlugin

import org.bukkit.command.CommandSender

class NickNameManager {
    private val setNickNamePermission = "nickName.setting"
    private val adminPermission = "nickName.admin"

    fun isChangeName(sender: CommandSender): Boolean {
        return sender.hasPermission(setNickNamePermission) || sender.hasPermission(adminPermission)
    }

    fun isAdmin(sender: CommandSender): Boolean {
        return sender.hasPermission(adminPermission)
    }
}
