package com.googry.fcmchat.util.bus

import com.googry.fcmchat.util.bus.model.BusEvent
import io.reactivex.subjects.PublishSubject

object RxBus {
    val bus = PublishSubject.create<BusEvent>()

    fun sendEvent(event: BusEvent) {
        bus.onNext(event)
    }

}