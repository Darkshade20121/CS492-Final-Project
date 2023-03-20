package com.example.riot.data

data class Match(
    val metadata: MatchMetadata,
    val players: MatchPlayerData
)

data class MatchMetadata(
    val map: String,
    val game_version: String,
    val game_length: Int,
    val game_start: Long,
    val game_start_patched: String,
    val rounds_played: Int,
    val mode: String,
    val queue: String,
    val season_id: String,
    val platform: String,
    val matchid: String,
    val region: String,
    val cluster: String
)

data class MatchPlayerData(
    val all_players: List<MatchPlayer>
)

data class MatchPlayer(
    val puuid: String,
    val name: String,
    val tag: String,
    val team: String,
    val character: String,
    val stats: MatchPlayerStats,
    val economy: MatchPlayerEconomy,
    val abilities: MatchPlayerAbilities
)

data class MatchPlayerStats(
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val score: Int
)

data class MatchPlayerEconomy(
    val loadout_value: Int,
    val spent: Int
)

data class MatchPlayerAbilities(
    val grenade_damages: List<Int>,
    val ability_uses: Map<String, Int>
)
