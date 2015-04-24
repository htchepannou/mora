package tchepannou.mora.core.util;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Model;

import java.util.Comparator;
import java.util.List;

public class ComparatorById<T extends Model> implements Comparator<T> {
    //-- Attributes
    private List<Long> ids;

    //-- Constructor
    public ComparatorById(List<Long> ids){
        Preconditions.checkArgument(ids != null, "ids == null");

        this.ids = ids;
    }

    //-- Comparator overrides
    @Override
    public int compare(T model1, T model2) {
        int index1 = ids.indexOf(model1.getId());
        int index2 = ids.indexOf(model2.getId());
        if (index1 < 0 || index2 < 0){
            return Integer.MAX_VALUE;
        } else {
            return index1 - index2;
        }
    }
}
