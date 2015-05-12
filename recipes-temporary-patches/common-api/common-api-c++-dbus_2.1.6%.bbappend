#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# This fix has been pushed to meta-ivi but not yet merged
# See https://bugs.genivi.org/show_bug.cgi?id=226

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden','${CXXFLAGS}', d)}"

SRC_URI = "git://github.com/Pelagicore/common-api-dbus-runtime.git;protocol=https;branch=master"
SRCREV = "d135374a34cdc9d13515e114acdfc4ec6cd2858c"

# We depend on the code generator to make sure recipes depending on us can also use the code generator
DEPENDS += "common-api-c++-dbus-codegen-native"
