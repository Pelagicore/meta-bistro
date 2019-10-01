#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
inherit update-alternatives

ALTERNATIVE_qtsystems-tools = "servicefw sfwlisten"
ALTERNATIVE_TARGET[servicefw] = "${OE_QMAKE_PATH_BINS}/servicefw"
ALTERNATIVE_TARGET[sfwlisten] = "${OE_QMAKE_PATH_BINS}/sfwlisten"
