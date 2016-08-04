#
#   Copyright (C) 2015 Pelagicore AB
#
require ${PN}.inc

PR = "${INC_PR}.0"

SRC_URI += " \
	git://git.gitorious.org/qt/qtpim.git;protocol=https \
	"

S = "${WORKDIR}/git/"

SRCREV = "7bd0f9ad742558cf4b33aa7e372713fdb680d2ad"

