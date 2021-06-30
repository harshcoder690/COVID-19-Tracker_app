package com.example.covid19_tracker

data class Response(
	val tested: List<TestedItem?>? = null
)

data class TestedItem(
	val source3: String? = null,
	val totaldosesadministered: String? = null,
	val source4: String? = null,
	val positivecasesfromsamplesreported: String? = null,
	val healthcareworkersvaccinated2nddose: String? = null,
	val over60years1stdose: String? = null,
	val registrationflwhcw: String? = null,
	val samplereportedtoday: String? = null,
	val registrationabove45years: String? = null,
	val source: String? = null,
	val source2: String? = null,
	val totalrtpcrsamplescollectedicmrapplication: String? = null,
	val registrationonline: String? = null,
	val frontlineworkersvaccinated1stdose: String? = null,
	val over60years2nddose: String? = null,
	val testsconductedbyprivatelabs: String? = null,
	val healthcareworkersvaccinated1stdose: String? = null,
	val to60yearswithcoMorbidities1stdose: String? = null,
	val dailyrtpcrsamplescollectedicmrapplication: String? = null,
	val totaldosesavailablewithstates: String? = null,
	val totalsessionsconducted: String? = null,
	val updatetimestamp: String? = null,
	val totalpositivecases: String? = null,
	val registrationonspot: String? = null,
	val years1stdose: String? = null,
	val seconddoseadministered: String? = null,
	val totalsamplestested: String? = null,
	val totaldosesinpipeline: String? = null,
	val totalindividualsregistered: String? = null,
	val frontlineworkersvaccinated2nddose: String? = null,
	val firstdoseadministered: String? = null,
	val years2nddose: String? = null,
	val over45years1stdose: String? = null,
	val totalindividualsvaccinated: String? = null,
	val totalvaccineconsumptionincludingwastage: String? = null,
	val testedasof: String? = null,
	val totaldosesprovidedtostatesuts: String? = null,
	val registration1845years: String? = null,
	val over45years2nddose: String? = null,
	val to60yearswithcoMorbidities2nddose: String? = null,
	val totalindividualstested: String? = null
)

