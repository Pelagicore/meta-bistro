#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# Build without XKB
XKB_DEPENDS = ""

#select specific version from 'dev'-branch
SRCREV = "8dccc6ca445f5e28d25e6a6148e7389fbc18534f"
QT_MODULE_BRANCH="dev"

#reset uri(!) to remove ALL patches
SRC_URI = " \
            git://qt.gitorious.org/qt/qtwayland.git;branch=${QT_MODULE_BRANCH} \
"
