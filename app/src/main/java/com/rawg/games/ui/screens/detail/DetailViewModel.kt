package com.rawg.games.ui.screens.detail

import androidx.lifecycle.ViewModel
import com.rawg.games.domain.model.GameDetails
import com.rawg.games.domain.usecase.GetGameDetailsUseCase
import com.rawg.games.utils.Result
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
) : ViewModel() {
    internal val gameDetailsSubject = BehaviorSubject.createDefault<Result<GameDetails>>(Result.Loading)

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getGameDetails(id: Int) {
        val getGameDetailsDisposable = getGameDetailsUseCase(id)
            .flatMapCompletable {
                Completable.fromAction {
                    gameDetailsSubject.onNext(Result.Success(it))
                }
            }
            .doOnError {
                gameDetailsSubject.onNext(Result.Error(it.message ?: "Unknown Error"))
            }
            .subscribe()

        disposable.add(getGameDetailsDisposable)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}