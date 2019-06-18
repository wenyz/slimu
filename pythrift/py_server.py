__author__ = "作者"

from py.thrift.generated import PersonService
from PersonServiceImpl import PersonServiceImpl

from thrift import Thrift

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from thrift.server import TServer


try:
    personServiceHandler = PersonServiceImpl()
    processor = PersonService.Processor(personServiceHandler)

    #TCP协议版本不同 java用的时TCP，python默认用的是TCPV6 所以最好加上IP，不要用localhost
    serverSocket = TSocket.TServerSocket(host="127.0.0.1",port=8899)
    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TCompactProtocol.TCompactProtocolFactory()

    server = TServer.TSimpleServer(processor,serverSocket,transportFactory,protocolFactory)

    server.serve()

except Thrift.TException as ex:
    print(ex.message)