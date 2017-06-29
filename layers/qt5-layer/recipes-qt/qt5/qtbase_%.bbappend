#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
inherit update-alternatives

# Add icu etc to PACKAGECONFIG in order to enable qtwebkit builds
# The ??= operator is used to assigned default values in qtbase.inc so we
# have to copy the default values and add our changes.

PACKAGECONFIG += " \
    accessibility \
    eglfs \
    gbm \
    gles2 \
    fontconfig \
    icu \
    kms \
    sql-sqlite \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"

ALTERNATIVE_qtbase-tools = "moc qdbuscpp2xml qdbusxml2cpp rcc uic"
ALTERNATIVE_TARGET[moc] = "${OE_QMAKE_PATH_BINS}/moc"
ALTERNATIVE_TARGET[qdbuscpp2xml] = "${OE_QMAKE_PATH_BINS}/qdbuscpp2xml"
ALTERNATIVE_TARGET[qdbusxml2cpp] = "${OE_QMAKE_PATH_BINS}/qdbusxml2cpp"
ALTERNATIVE_TARGET[rcc] = "${OE_QMAKE_PATH_BINS}/rcc"
ALTERNATIVE_TARGET[uic] = "${OE_QMAKE_PATH_BINS}/uic"
