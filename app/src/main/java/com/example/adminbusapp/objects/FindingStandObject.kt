package com.example.adminbusapp.objects

object FindingStandObject {

    var standList = arrayOf<String>()

    fun findStand(num : String){
        for((k,v) in busStandData.arr){
            if(k == num){
                standList = v
                break
            }
        }
    }

}