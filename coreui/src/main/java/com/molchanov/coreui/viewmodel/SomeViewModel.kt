import com.molchanov.core.domain.viewmodel.AppState
import com.molchanov.coreui.viewmodel.BaseViewModel

class SomeViewModel : BaseViewModel<AppState>() {
    override fun getData(page: Int) {
        //TODO Nothing
    }

    override fun reloadData() {
        //TODO Nothing
    }

    override fun searchData(searchWord: String) {
        //TODO Nothing
    }
}