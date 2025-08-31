package com.myapplication.data.mappers

import com.myapplication.data.database.CoinPriceInfoDbModel
import com.myapplication.domain.CoinPriceInfo
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class DbModelMapper {

    fun mapDbModelToEntity(coinPriceInfoDbModel: CoinPriceInfoDbModel): CoinPriceInfo {
        return CoinPriceInfo(
            coinPriceInfoDbModel.type,
            coinPriceInfoDbModel.market,
            coinPriceInfoDbModel.fromSymbol,
            coinPriceInfoDbModel.toSymbol,
            coinPriceInfoDbModel.flags,
            coinPriceInfoDbModel.price,
            convertTimestampToTime(coinPriceInfoDbModel.lastUpdate),
            coinPriceInfoDbModel.lastVolume,
            coinPriceInfoDbModel.lastVolumeTo,
            coinPriceInfoDbModel.lastTradeId,
            coinPriceInfoDbModel.volumeDay,
            coinPriceInfoDbModel.volumeDayTo,
            coinPriceInfoDbModel.volume24Hour,
            coinPriceInfoDbModel.volume24HourTo,
            coinPriceInfoDbModel.openDay,
            coinPriceInfoDbModel.highDay,
            coinPriceInfoDbModel.lowDay,
            coinPriceInfoDbModel.open24Hour,
            coinPriceInfoDbModel.high24Hour,
            coinPriceInfoDbModel.low24Hour,
            coinPriceInfoDbModel.lastMarket,
            coinPriceInfoDbModel.volumeHour,
            coinPriceInfoDbModel.volumeHourTo,
            coinPriceInfoDbModel.openHour,
            coinPriceInfoDbModel.highHour,
            coinPriceInfoDbModel.lowHour,
            coinPriceInfoDbModel.topTierVolume24Hour,
            coinPriceInfoDbModel.topTierVolume24HourTo,
            coinPriceInfoDbModel.change24Hour,
            coinPriceInfoDbModel.changePCT24Hour,
            coinPriceInfoDbModel.changeDay,
            coinPriceInfoDbModel.changePCTDay,
            coinPriceInfoDbModel.supply,
            coinPriceInfoDbModel.mktCap,
            coinPriceInfoDbModel.totalVolume24Hour,
            coinPriceInfoDbModel.totalVolume24HourTo,
            coinPriceInfoDbModel.totalTopTierVolume24Hour,
            coinPriceInfoDbModel.totalTopTierVolume24HourTo,
            BASE_IMAGE_URL + coinPriceInfoDbModel.imageUrl
        )
    }

    fun mapListDbModelToEntity(list: List<CoinPriceInfoDbModel>): List<CoinPriceInfo> = list.map {
        mapDbModelToEntity(it)
    }


    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }

}