#
#   Copyright (C) 2015 Pelagicore AB
#
require ${PN}-common.inc

DEPENDS += "common-api-dbus-integration"

inherit cmake

S = "${WORKDIR}/git/cmake"

FILES_${PN}-dev += "${libdir}/cmake/*"

ALLOW_EMPTY_${PN} = "1"

