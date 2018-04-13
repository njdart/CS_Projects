class BootstrapNavigationRenderer < SimpleNavigation::Renderer::Base
  def render(item_container)

    content_tag :ul, li_tags(item_container).join(join_with), {
        id: item_container.dom_id,
        class: "nav nav-tabs #{item_container.dom_class}"
    }
  end

  protected

  def li_tags(item_container)
    item_container.items.each_with_object([]) do |item, list|

      # TODO: implement drop downs
      # https://getbootstrap.com/components/#nav-dropdowns
      if include_sub_navigation?(item)
      #   options = { method: item.method }.merge(item.html_options.except(:class, :id))
      #   list << content_tag(:li, link_to(item.name), item.url, options)
      #   list.concat li_tags(item.sub_navigation)
      else
        list << content_tag(:li,
                            content_tag(:a, item.name, href: item.url),
                            {
                                class: item.selected? ? 'active' : '' ,
                                role: 'presentation'
                            }
        )
      end
    end
  end

  def join_with
    # @join_with ||= options[:join_with] || '<span class="divider">/</span>'.html_safe
  end
end