package filter

import play.api.http.HttpFilters

import javax.inject.Inject

class Filters @Inject()(globalLoggingFilter: GlobalLoggingFilter) extends HttpFilters {
    // ほかにFilter処理があればここに追加する
    override val filters: Seq[GlobalLoggingFilter] = Seq(globalLoggingFilter)
}
