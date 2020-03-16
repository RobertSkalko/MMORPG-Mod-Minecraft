package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class TentRoomAdder extends BaseRoomAdder {

    public TentRoomAdder() {
        super(RoomGroup.TENT);
    }

    @Override
    public void addAllRooms() {

        add("2", RoomType.CURVED_HALLWAY);
        add("4", RoomType.CURVED_HALLWAY);

        add("6", RoomType.END);

        add("0", RoomType.ENTRANCE);

        add("3", RoomType.FOUR_WAY);

        add("1", RoomType.STRAIGHT_HALLWAY);
        add("trader0", RoomType.STRAIGHT_HALLWAY).setTrader();

        add("boss1", RoomType.TRIPLE_HALLWAY).setBoss();

    }
}
