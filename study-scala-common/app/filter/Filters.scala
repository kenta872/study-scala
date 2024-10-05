package filter

import play.api.http.HttpFilters

import javax.inject.Inject

class Filters @Inject()(requestLoggingFilter: RequestLoggingFilter) extends HttpFilters {
    // ほかにFilter処理があればここに追加する
    override val filters: Seq[RequestLoggingFilter] = Seq(requestLoggingFilter)
}
