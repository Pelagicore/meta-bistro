addtask rootfs before do_generate_readme
addtask populate_sdk before do_generate_readme
addtask generate_readme before do_sed_readme
addtask sed_readme before do_package_sdk
addtask package_sdk after sed_readme

SDK_OUTPUTNAME = "${SDK_NAME}-sdk-${SDK_VERSION}"
DEPLOY_DIR = "${TMPDIR}/deploy/"
FINAL_IMAGE_PATH = "images/${MACHINE}/${IMAGE_BASENAME}-${MACHINE}.sdcard"
FINAL_TOOLCHAIN_PATH = "sdk/${TOOLCHAIN_OUTPUTNAME}.sh"

SDK_README ?= "README"
SDK_README_WORK_PATH ?= "${WORKDIR}/README"

do_generate_readme() {
    if [ -r "${SDK_README}" ]; then
        cp ${SDK_README} ${SDK_README_WORK_PATH}
    fi
}

do_sed_readme() {
    if [ -r "${SDK_README_WORK_PATH}" ]; then
        sed -i "s,%%%MACHINE%%%,${MACHINE},g" ${SDK_README_WORK_PATH}
        sed -i "s,%%%IMAGE%%%,${FINAL_IMAGE_PATH},g" ${SDK_README_WORK_PATH}
        sed -i "s,%%%SDK%%%,${FINAL_TOOLCHAIN_PATH},g" ${SDK_README_WORK_PATH}
    fi
}

do_package_sdk() {
    tar -cvSh -C ${DEPLOY_DIR} -f ${SDK_DEPLOY}/${SDK_OUTPUTNAME}.tar ${FINAL_IMAGE_PATH} ${FINAL_TOOLCHAIN_PATH}

    if [ -r ${SDK_README_WORK_PATH} ]; then
        tar -r -C ${WORKDIR} -f ${SDK_DEPLOY}/${SDK_OUTPUTNAME}.tar `basename ${SDK_README_WORK_PATH}`
    fi
}


