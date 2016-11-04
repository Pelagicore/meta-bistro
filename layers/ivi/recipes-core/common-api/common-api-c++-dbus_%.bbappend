#
#   Copyright (C) 2016 Pelagicore AB
#

# The recipe sets an inexistent branch in the URL
SRC_URI = "git://git.projects.genivi.org/ipc/common-api-dbus-runtime.git;protocol=http"

# add native dependencies for CommonAPI code generator
DEPENDS += "\
    common-api-cmdline \
    common-api-cmdline-codegen-native \
    common-api-c++-dbus-codegen-native \
"
