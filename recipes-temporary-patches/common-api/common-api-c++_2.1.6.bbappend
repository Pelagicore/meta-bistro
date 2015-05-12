#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# This fix has been pushed to meta-ivi but not yet merged
# See https://bugs.genivi.org/show_bug.cgi?id=226

CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden','${CXXFLAGS}', d)}"
