package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import java.util.List;
import java.util.Map;

public interface AbstractComplexEntity {

    Map<AbstractResponse, List<AbstractResponse>> toDomain();
}
