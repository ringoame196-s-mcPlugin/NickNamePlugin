package com.github.ringoame196_s_mcPlugin

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TabCompleter : TabCompleter {
    override fun onTabComplete(commandSender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        return when (args.size) {
            0 -> mutableListOf(commandSender.name)
            1 -> Bukkit.getOnlinePlayers().map { it.name }.toMutableList()
            else -> mutableListOf()
        }
    }
}
