package mvs.translator.utils.network

import androidx.lifecycle.MutableLiveData

interface OnlineRepository {

    val availableNetworks: MutableLiveData<Boolean>
}
