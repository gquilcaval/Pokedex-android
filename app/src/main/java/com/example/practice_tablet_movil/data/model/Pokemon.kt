package com.example.practice_tablet_movil.data.network.model

import com.example.practice_tablet_movil.data.model.Stat
import com.example.practice_tablet_movil.data.model.StatReponse
import com.example.practice_tablet_movil.data.model.TypeReponse

data class Pokemon (val id: Int, val name: String, val weight: Int, val height: Int,val types:List<TypeReponse>,val stats: List<StatReponse>)