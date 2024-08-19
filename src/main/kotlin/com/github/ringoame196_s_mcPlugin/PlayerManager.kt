package com.github.ringoame196_s_mcPlugin

import org.bukkit.entity.Player

class PlayerManager {
    fun changeName(player: Player, nickName: String) {
        player.setDisplayName(nickName)
        player.setPlayerListName(nickName)
    }
}
