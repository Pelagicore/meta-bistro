#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
inherit update-alternatives

ALTERNATIVE_qt3d-tools = "qglinfo"
ALTERNATIVE_TARGET[qglinfo] = "${OE_QMAKE_PATH_BINS}/qglinfo"
