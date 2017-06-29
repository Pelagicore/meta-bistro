#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
require common-api-cmdline-common.inc

inherit cmake maven-properties

S = "${WORKDIR}/git/host-tools"

FILES_${PN} += "${datadir}/* \
${libdir}/cmake/* \
"

ALLOW_EMPTY_${PN} = "1"

BBCLASSEXTEND = "native nativesdk"

