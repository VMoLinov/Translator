package mvs.translator.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : ISchedulerProvider {

    override val ui: Scheduler = AndroidSchedulers.mainThread()
    override val io: Scheduler = Schedulers.io()
}
