#
#   Copyright (C) 2015-2017 Pelagicore AB
#
inherit update-alternatives

ALTERNATIVE_qtdeclarative-tools = "qmlmin qmlscene qmltestrunner"
ALTERNATIVE_TARGET[qmlmin] = "${OE_QMAKE_PATH_BINS}/qmlmin"
ALTERNATIVE_TARGET[qmlscene] = "${OE_QMAKE_PATH_BINS}/qmlscene"
ALTERNATIVE_TARGET[qmltestrunner] = "${OE_QMAKE_PATH_BINS}/qmltestrunner"
