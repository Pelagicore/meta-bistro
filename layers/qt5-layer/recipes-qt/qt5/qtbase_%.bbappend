#
#   Copyright (C) 2015 Pelagicore AB
#
inherit update-alternatives

# Add icu etc to PACKAGECONFIG in order to enable qtwebkit builds
# The ??= operator is used to assigned default values in qtbase.inc so we
# have to copy the default values and add our changes.

PACKAGECONFIG += " \
    accessibility \
    gles2 \
    icu \
    openssl \
    sql-sqlite \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"

ALTERNATIVE_qtbase-tools = "moc qdbuscpp2xml qdbusxml2cpp qdoc rcc uic"
ALTERNATIVE_TARGET[moc] = "${OE_QMAKE_PATH_BINS}/moc"
ALTERNATIVE_TARGET[qdbuscpp2xml] = "${OE_QMAKE_PATH_BINS}/qdbuscpp2xml"
ALTERNATIVE_TARGET[qdbusxml2cpp] = "${OE_QMAKE_PATH_BINS}/qdbusxml2cpp"
ALTERNATIVE_TARGET[qdoc] = "${OE_QMAKE_PATH_BINS}/qdoc"
ALTERNATIVE_TARGET[rcc] = "${OE_QMAKE_PATH_BINS}/rcc"
ALTERNATIVE_TARGET[uic] = "${OE_QMAKE_PATH_BINS}/uic"
