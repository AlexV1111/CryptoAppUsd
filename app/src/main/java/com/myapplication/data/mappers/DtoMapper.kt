package com.myapplication.data.mappers

import com.myapplication.data.DTO.CoinPriceInfoDto
import com.myapplication.data.database.CoinPriceInfoDbModel

class DtoMapper {

    fun mapDtoToDbModel(coinPriceInfoDto: CoinPriceInfoDto): CoinPriceInfoDbModel {
        return CoinPriceInfoDbModel(
            coinPriceInfoDto.type,
            coinPriceInfoDto.market,
            coinPriceInfoDto.fromSymbol,
            coinPriceInfoDto.toSymbol,
            coinPriceInfoDto.flags,
            coinPriceInfoDto.price,
            coinPriceInfoDto.lastUpdate,
            coinPriceInfoDto.lastVolume,
            coinPriceInfoDto.lastVolumeTo,
            coinPriceInfoDto.lastTradeId,
            coinPriceInfoDto.volumeDay,
            coinPriceInfoDto.volumeDayTo,
            coinPriceInfoDto.volume24Hour,
            coinPriceInfoDto.volume24HourTo,
            coinPriceInfoDto.openDay,
            coinPriceInfoDto.highDay,
            coinPriceInfoDto.lowDay,
            coinPriceInfoDto.open24Hour,
            coinPriceInfoDto.high24Hour,
            coinPriceInfoDto.low24Hour,
            coinPriceInfoDto.lastMarket,
            coinPriceInfoDto.volumeHour,
            coinPriceInfoDto.volumeHourTo,
            coinPriceInfoDto.openHour,
            coinPriceInfoDto.highHour,
            coinPriceInfoDto.lowHour,
            coinPriceInfoDto.topTierVolume24Hour,
            coinPriceInfoDto.topTierVolume24HourTo,
            coinPriceInfoDto.change24Hour,
            coinPriceInfoDto.changePCT24Hour,
            coinPriceInfoDto.changeDay,
            coinPriceInfoDto.changePCTDay,
            coinPriceInfoDto.supply,
            coinPriceInfoDto.mktCap,
            coinPriceInfoDto.totalVolume24Hour,
            coinPriceInfoDto.totalVolume24HourTo,
            coinPriceInfoDto.totalTopTierVolume24Hour,
            coinPriceInfoDto.totalTopTierVolume24HourTo,
            coinPriceInfoDto.imageUrl
        )
    }

    fun mapListDtoToDbModel(list: List<CoinPriceInfoDto>): List<CoinPriceInfoDbModel> = list.map {
        mapDtoToDbModel(it)
    }

}