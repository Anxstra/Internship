package com.anxstra.entities;

import com.anxstra.entities.enums.ShowType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ShowFor {

    private ShowType type;

    private List<String> users;
}
