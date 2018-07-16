DESCRIPTION = "Driver/HAL to build a gateway using a concentrator board based on Semtech SX1301 multi-channel modem and SX1257/SX1255 RF transceivers"
HOMEPAGE = "https://github.com/Lora-net/lora_gateway"
LICENSE = "SEMTECH"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a2bdef95625509f821ba00460e3ae0eb"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/Lora-net/lora_gateway.git;protocol=https"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "^rpi$"
CFLAGS_prepend = "-I${S}/libloragw/inc -I${S}/util_pkt_logger/inc "

do_compile() {
    oe_runmake
}

do_install(){
  install -d -m 0755 ${D}${base_prefix}/opt
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway/util_pkt_logger
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway/util_tx_test
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway/util_tx_test
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway/util_tx_continuous
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway/util_spi_stress
  install -d -m 0755 ${D}${base_prefix}/opt/lora_gateway/util_lbt_test

  install -m 0755 reset_lgw.sh    ${D}${base_prefix}/opt/lora_gateway/
  install -m 0755 util_pkt_logger/util_pkt_logger    ${D}${base_prefix}/opt/lora_gateway/util_pkt_logger/
  install -m 0755 util_pkt_logger/global_conf.json    ${D}${base_prefix}/opt/lora_gateway/util_pkt_logger/
  install -m 0755 util_pkt_logger/local_conf.json    ${D}${base_prefix}/opt/lora_gateway/util_pkt_logger/
  install -m 0755 util_tx_test/util_tx_test    ${D}${base_prefix}/opt/lora_gateway/util_tx_test/
  install -m 0755 util_tx_continuous/util_tx_continuous    ${D}${base_prefix}/opt/lora_gateway/util_tx_continuous/
  install -m 0755 util_spi_stress/util_spi_stress    ${D}${base_prefix}/opt/lora_gateway/util_spi_stress/
  install -m 0755 util_lbt_test/util_lbt_test    ${D}${base_prefix}/opt/lora_gateway/util_lbt_test/
}

FILES_${PN} = "${base_prefix}/opt/*"

deltask do_package_qa
INSANE_SKIP_${PN} += "installed-vs-shipped"

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""
