# This allow reuse of Qt paths

inherit qmake5_base populate_sdk_qt5

QTCREATORSCRIPT = "qtCreatorConfig.sh"
QTCREATORSCRIPTPATH = "${SDK_OUTPUT}/${SDKPATH}/${QTCREATORSCRIPT}"
REAL_MULTIMACH_TARGET_SYS = "${TUNE_PKGARCH}${TARGET_VENDOR}-${TARGET_OS}"

toolchain_create_sdk_env_script_append () {
	cat << "EOF" > ${QTCREATORSCRIPTPATH}
#!/bin/bash

USAGE="Usage: `basename $0` [-r] <qt-creator-binary-path> <kit-name> \n Options: \n   -r: Remove the SDK from QtCreator"
removeMode=0;

# Parse command line options.
while getopts rh OPT; do
    case "$OPT" in
        r)
            removeMode=1
            ;;
        h)
            # getopts issues an error message
            echo -e $USAGE >&2
            exit 1
            ;;
    esac
done

# Remove the switches we parsed above.
shift `expr $OPTIND - 1`

# We want at least one non-option argument. 
# Remove this block if you don't need it.
if [ $# -eq 0 ]; then
    echo -e $USAGE >&2
    exit 1
fi

qtCreatorBinPath=$1
name=$2

toolchainId="ProjectExplorer.ToolChain.Gcc:$name";
qtId=$name"_qt";
kitId=$name"_kit";

CUR_DIR=`dirname $0`
source $CUR_DIR/environment-setup-${REAL_MULTIMACH_TARGET_SYS}

abi="${TARGET_ARCH}-linux-generic-elf-${SITEINFO_BITS}bit"
compiler=`which $CXX`
qmake=`which qmake-sdk`
gdb=`which $GDB`
sysroot=$SDKTARGETSYSROOT

if [ $removeMode -eq 0 ]; then

    $qtCreatorBinPath/sdktool addTC --id "$toolchainId" --name "$name" --abi $abi --path $compiler > /dev/null
    if [ $? -ne 0 ]; then
        echo adding Toolchain failed
        exit 1
    fi
    $qtCreatorBinPath/sdktool addQt --id "$qtId" --name "$name" --type "RemoteLinux.EmbeddedLinuxQt" --qmake $qmake > /dev/null
    if [ $? -ne 0 ]; then
        echo adding Qt failed
        $qtCreatorBinPath/sdktool rmTC --id "$toolchainId" > /dev/null
        exit 1
    fi
    $qtCreatorBinPath/sdktool addKit --id "$kitId" --name "$name" --debuggerengine 1 --debugger $gdb --devicetype GenericLinuxOsType --sysroot $sysroot --toolchain "$toolchainId" --qt "$qtId" --mkspec "${OE_QMAKE_PLATFORM}" > /dev/null
    if [ $? -ne 0 ]; then
        echo adding Kit failed
        $qtCreatorBinPath/sdktool rmTC --id "$toolchainId" > /dev/null
        $qtCreatorBinPath/sdktool rmQt --id "$qtId" > /dev/null
        exit 1
    fi

    echo "Added Kit successfully";

else
        $qtCreatorBinPath/sdktool rmTC --id "$toolchainId" > /dev/null
        $qtCreatorBinPath/sdktool rmQt --id "$qtId" > /dev/null
        $qtCreatorBinPath/sdktool rmKit --id "$kitId" > /dev/null

        echo "Removed Kit successfully";
fi
EOF

	chmod +x ${QTCREATORSCRIPTPATH}
}

