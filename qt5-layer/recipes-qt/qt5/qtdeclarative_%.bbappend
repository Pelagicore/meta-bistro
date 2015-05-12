#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
inherit update-alternatives

ALTERNATIVE_qtdeclarative-tools = "qmlbundle qmlmin qmlscene qmltestrunner"
ALTERNATIVE_TARGET[qmlbundle] = "${OE_QMAKE_PATH_BINS}/qmlbundle"
ALTERNATIVE_TARGET[qmlmin] = "${OE_QMAKE_PATH_BINS}/qmlmin"
ALTERNATIVE_TARGET[qmlscene] = "${OE_QMAKE_PATH_BINS}/qmlscene"
ALTERNATIVE_TARGET[qmltestrunner] = "${OE_QMAKE_PATH_BINS}/qmltestrunner"
