pragma solidity ^0.6.4;

contract LogContract {
    string logId1;
    address addr1;
    uint public roomIndex;
    mapping(address => mapping(uint => Log)) stores;
    mapping(uint => address) roomIdInStore;

    struct Log {
        uint index;
        string logId;
        string content;
        string userId;
        address ad;
    }
    // 构造函数
    constructor() public {
        roomIndex = 0;
        addr1 = msg.sender;
    }

    // 保存数据
    function addRoomToStore(string memory id, string memory content, string memory userId) public {
        roomIndex += 1;
        Log memory log = Log(roomIndex, id, content, userId, addr1);
//        Log memory room = Log(id, content, userId, addr1);
        stores[msg.sender][roomIndex] = log;
        roomIdInStore[roomIndex] = msg.sender;
    }

    // 提取数据
    function getRoom(uint id) public view returns (uint, string memory, string memory, string memory, address) {
        Log memory log = stores[roomIdInStore[id]][id];
//        logId1 == log.logId;
        return (log.index, log.logId, log.content, log.userId, addr1);
    }

}
