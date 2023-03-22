package com.example.riot.data

import java.io.Serializable

data class MatchResponse(
    val status: Int,
    val data: List<MatchData>
) : Serializable

data class MatchData(
    val metadata: MatchMetadata,
    val players: Players,
    val teams: Teams
) : Serializable

data class Teams(
    val red: Team,
    val blue: Team
) : Serializable

data class Team(
    val has_won: Boolean,
    val rounds_won: Int,
    val rounds_lost: Int
) : Serializable


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
) : Serializable

data class Players(
    val all_players: List<Player>
) : Serializable

data class Player(
    val puuid: String,
    val name: String,
    val tag: String,
    val team: String,
    val level: Int,
    val character: String,
    val currenttier: Int,
    val currenttier_patched: String,
    val player_card: String,
    val player_title: String,
    val party_id: String,
    val session_playtime: SessionPlaytime,
    val behavior: Behavior,
    val platform: Platform,
    val ability_casts: AbilityCasts,
    val assets: Assets,
    val stats: Stats,
    val economy: Economy,
    val damage_made: Int,
    val damage_received: Int
) : Serializable

data class SessionPlaytime(
    val minutes: Int,
    val seconds: Int,
    val milliseconds: Int
) : Serializable

data class Behavior(
    val afk_rounds: Double,
    val friendly_fire: FriendlyFire,
    val rounds_in_spawn: Int
) : Serializable

data class FriendlyFire(
    val incoming: Int,
    val outgoing: Int
) : Serializable

data class Platform(
    val type: String,
    val os: OS
) : Serializable

data class OS(
    val name: String,
    val version: String
) : Serializable

data class AbilityCasts(
    val c_cast: Int,
    val q_cast: Int,
    val e_cast: Int,
    val x_cast: Int
) : Serializable

data class Assets(
    val card: Card,
    val agent: Agent
) : Serializable

data class Card(
    val small: String,
    val large: String,
    val wide: String
) : Serializable

data class Agent(
    val small: String,
    val bust: String,
    val full: String,
    val killfeed: String
) : Serializable

data class Stats(
    val score: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val bodyshots: Int,
    val headshots: Int,
    val legshots: Int
) : Serializable

data class Economy(
    val spent: Spent,
    val loadout_value: LoadoutValue
) : Serializable

data class Spent(
    val overall: Int,
    val average: Int
) : Serializable

data class LoadoutValue(
    val overall: Int,
    val average: Int
) : Serializable

// Specifics to what we wanna display here
data class MatchInfo(
    val map: String,
    val players: List<String>,
    val kills: List<Int>,
    val deaths: List<Int>,
    val team: List<String>,
    val teams: Teams
) : Serializable

